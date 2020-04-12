package cn.ac.iscas.dao;

import cn.ac.iscas.entity.Events;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EventsMapper {


    //add an event
    int addEvent(Events record);

    //select event by ID
    Events selectEventById(Integer id);

    //select event by teacher ID
    Events[] selectEventsByTeacherId(Integer tid);

    //update event by id
    int updateEventById(Events record);

    //delete event by id
    int deleteEventById(Integer id);

    //select event by date
    Events[] selectEventsByDate(Date date);
}