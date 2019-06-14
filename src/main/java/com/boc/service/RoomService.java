package com.boc.service;

import com.boc.domain.Room;
import com.boc.domain.RoomReserve;
import com.boc.repository.RoomRepository;
import com.boc.repository.RoomReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by YinChong on 2018/9/4.
 */

@Service
public class RoomService {
    @Autowired
    private RoomReserveRepository roomReserveRepository;

    @Autowired
    private RoomRepository roomRepository;

    /**
     * 查询会议室列表
     * @return
     */
    public List<Room> roomList(){
        return roomRepository.findAll();
    }


    /**
     * 根据会议室id和日期，查询预定列表
     * @return
     * @param roomId
     */
    public List<RoomReserve> findScheInfoByRoomAndDate(String roomId, String date){
        return roomReserveRepository.findByRoomIdAndReserveDate(roomId, date);
    }


    /**
     * 保存一次预定
     * @return
     * @param roomReserveData
     */
    public RoomReserve saveReserve(RoomReserve roomReserveData){
        System.out.println("插入一条预定记录："+ roomReserveData);
        RoomReserve roomReserve = new RoomReserve();
        roomReserve.setRoomId(roomReserveData.getRoomId());
        roomReserve.setRoomName(roomReserveData.getRoomName());
        roomReserve.setReserveDate(roomReserveData.getReserveDate());
        roomReserve.setStartTime(roomReserveData.getStartTime());
        roomReserve.setEndTime(roomReserveData.getEndTime());
        roomReserve.setSubject(roomReserveData.getSubject());
        roomReserve.setUsername(roomReserveData.getUsername());
        roomReserve.setUserNo(roomReserveData.getUserNo());
        roomReserve.setReserveStatus(1);

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间：" + sdf.format(d));

        roomReserve.setCreateTime(sdf.format(d));
        return roomReserveRepository.save(roomReserve);
    }


    /**
     * 根据预定人userId，查询这个人的预定列表
     * @return
     * @param userNo
     */
    public List<RoomReserve> findScheInfoByUser(String userNo){
        return roomReserveRepository.findByUserNo(userNo);
    }

    /**
     * 删除预定
     * @return
     * @param id
     */
    @Transactional
    public void deleteScheById(String id){
        roomReserveRepository.deleteById(id);
    }

    /**
     * 取消预定
     * @return
     * @param id
     */
    @Transactional
    public void cancelScheById(String id){
        roomReserveRepository.cancelById(id);
    }

}
