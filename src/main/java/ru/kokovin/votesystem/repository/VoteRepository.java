package ru.kokovin.votesystem.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kokovin.votesystem.model.Vote;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    //    https://stackoverflow.com/a/46013654/548473
    @EntityGraph(attributePaths = {"restaurant","user"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT v FROM Vote v WHERE v.user.id =:userId")
    public List<Vote> getAllByUserId(@Param("userId") int userId);

    @EntityGraph(attributePaths = {"restaurant","user"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT v FROM Vote v WHERE v.id =:id")
    public Optional<Vote> getById(@Param("id") int id);

    @EntityGraph(attributePaths = {"restaurant", "user"}, type = EntityGraph.EntityGraphType.LOAD)
    public List<Vote> findAll();

    @Query("SELECT v FROM Vote v WHERE v.user.id =:userId AND v.voteDateTime>=:begin AND v.voteDateTime<=:end")
    public Vote getTodaysVoteByUserId(@Param("userId") int userId,
                                      @Param("begin") LocalDateTime begin,
                                      @Param("end") LocalDateTime end);

}
