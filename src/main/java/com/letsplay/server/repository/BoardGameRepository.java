package com.letsplay.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.letsplay.server.entity.BoardGame;

@Repository
public interface BoardGameRepository extends JpaRepository<BoardGame, Long>{

	@Query(" Select distinct b from BoardGame b join b.players p join p.groups g where g.id = :id")
	List<BoardGame> findByGroup(@Param("id") Long id);
	
	BoardGame findByNameOrderByName(String name);
	
}
