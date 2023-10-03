package com.betbillions.userservice.infrastructure.driver.r2dbc;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Repository
public interface UsersReactiveRepository extends ReactiveCrudRepository<UserEntity, String>, ReactiveQueryByExampleExecutor<UserEntity> {

    Flux<UserEntity> findAllBy(Pageable pageable);
    Flux<UserEntity> findByIdIn(Pageable pageable,List<String> uuid);
    Mono<Long> countByIdIn(List<String> uuid);
    @Query(value = """
    WITH RECURSIVE user_team AS (
      SELECT u.id, u.full_name, u.phone, u.username, u.created_at, 0 AS level
      FROM users u
      WHERE u.id = :uuid
      UNION ALL
      SELECT u.id, u.full_name, u.phone, u.username, u.created_at, ut.level + 1 AS level
      FROM users u 
      INNER JOIN user_team ut ON u.parent_id = ut.id
      WHERE ut.level < 4
    )
    SELECT * FROM user_team WHERE id <> :uuid""")
    Flux<UserEntity> findUserAndDescendantsTeam(@Param("id") UUID uuid);

    @Query(value = """
    WITH RECURSIVE user_team AS (
      SELECT u.id, u.full_name, u.phone, u.username, u.created_at, u.parent_id, 0 AS level ,  u.commission
      FROM users u
      WHERE u.id = :uuid
      UNION ALL
      SELECT u.id, u.full_name, u.phone, u.username, u.created_at, u.parent_id, ut.level + 1 AS level , u.commission
      FROM users u
      INNER JOIN user_team ut ON u.id = ut.parent_id
    )
    SELECT * FROM user_team
    WHERE level <= :level and id <> :uuid and commission=true ;
    """)
    Flux<UserEntity> findUserAndParents(@Param("id")  UUID uuid , @Param("level") Integer level);


}
