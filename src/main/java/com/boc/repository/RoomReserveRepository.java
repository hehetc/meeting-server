package com.boc.repository;

import com.boc.domain.RoomReserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by YinChong on 2018/9/4.
 */
public interface RoomReserveRepository extends JpaRepository<RoomReserve, String> {
    public List<RoomReserve> findByRoomIdAndReserveDate(String roomId, String date);
    void deleteById(String id);

    @Modifying
    @Query(value="select * from room_reserve WHERE user_no = :userNo order by reserve_status DESC, create_time DESC",nativeQuery=true)
    List<RoomReserve> findByUserNo(@Param("userNo") String userNo);

    @Modifying
    @Query(value="update RoomReserve r set r.reserveStatus = 0 WHERE id = :id")
    public void cancelById(@Param("id") String id);
}
