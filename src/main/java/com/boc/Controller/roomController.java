package com.boc.Controller;

import com.boc.domain.Room;
import com.boc.domain.RoomReserve;
import com.boc.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by YinChong on 2018/9/3.
 */

@RestController
@RequestMapping("/meeting")
public class roomController {

    @Autowired
    private RoomService roomService;


    /**
     * 查询所有会议室列表
     * @return
     */
    @GetMapping(value = "rooms")
    public List<Room> roomList(){
        return roomService.roomList();
    }

    /**
     * 根据会议室id和日期，查询预定列表
     * @return
     */
    @GetMapping(value = "/rooms/findScheInfoByRoomAndDate")
    public List<RoomReserve> findScheInfoByRoomAndDate(@RequestParam(value="roomId", required = false) String roomId, @RequestParam(value="reserveDate", required = false) String reserveDate){
        System.out.println(reserveDate);
        return roomService.findScheInfoByRoomAndDate(roomId,reserveDate);
    }

    /**
     * 提交一个会议室的预定
     * @return
     */
    @PostMapping(value = "/rooms/submitRoomReserve")
    public RoomReserve roomReserve(@RequestBody RoomReserve roomReserve){
        System.out.println(roomReserve);
        return roomService.saveReserve(roomReserve);
    }

    /**
     * 根据预定人userId，查询这个人的预定列表
     * @return
     */
    @GetMapping(value = "/rooms/findScheInfoByUser")
    public List<RoomReserve> findScheInfoByUser(@RequestParam(value="userId", required = false) String userNo){
        System.out.println("根据用户工号："+ userNo + "查询预定记录");
        return roomService.findScheInfoByUser(userNo);
    }

    /**
     * 根据预定的id流水号，物理删除一个预定
     * @return
     */
    @PostMapping(value = "/rooms/deleteScheById")
    public void deleteScheById(@RequestParam String id){
        System.out.println("根据以下id删除一条预定：");
        System.out.println(id);
        roomService.deleteScheById(id);
    }

    /**
     * 根据预定的id流水号，取消一个预定
     * @return
     */
    @PostMapping(value = "/rooms/cancelScheById")
    public void cancelScheById(@RequestParam("id") String id){
        System.out.println("根据以下id取消一条预定：");
        System.out.println(id);
        roomService.cancelScheById(id);
    }




//    //添加，以解决Date和String转换问题
//    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
//    }

}
