package com.example.demo.dao;

import com.example.demo.entity.Field;
import com.example.demo.entity.FieldCompositeId;
import com.example.demo.vo.GymAndField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FieldDao extends JpaRepository<GymAndField, FieldCompositeId>
, JpaSpecificationExecutor<Field> {
    @Query(value = "select new com.example.demo.vo.GymAndField" +
            "(g.gymId,g.gymName,g.startTime,g.endTime,f.fieldId,f.fieldName) " +
            "from Field f join Gym g " +
            "on f.gymId = ?1 and f.fieldId = ?2 " +
            "and f.gymId = g.gymId")
    GymAndField queryFieldById(int gid, int fid);


    /*@Query(value="select gym_id,gym_name,start_time,end_time,field_id,field_name " +
            "from field natural join \n" +
            "(select gym_id,gym_name,start_time,end_time " +
            "from gym" +
            "where start_time <= ? and end_time >= ?) as b " +
            "natural join " +
            " ( \tselect gym_id , field_id\n" +
            "      \tfrom field \n" +
            "      \twhere (gym_id , field_id) \n" +
            "        not in (\tselect gym_id ,field_id\n" +
            "        \t\t\tfrom reserve r \n" +
            "                \twhere r.endtime >= ? and r.starttime <= ? and r.date = ?\n" +
            "                )\n" +
            "  ) as a \n" +
            "where gym_id \n" +
            "     not in ( select gym_id\n" +
            "     \t\t  from course\n" +
            "              where course.weekday = ? and course.endtime >= ? and course.starttime <= ?)",nativeQuery = true)*/
    @Query(value = "select gym.gym_id as gymId,gym.gym_name as gymName,gym.start_time as startTime,gym.end_time as endTime,field.field_id as fieldId,field.field_name as fieldName " +
            "from gym natural join field \n" +
            "where gym.start_time <= :startTime and gym.end_time >= :endTime \n" +
            "and (gym_id, field_id) not in \n" +
            "\t( select gym_id ,field_id \n" +
            "\t\tfrom reserve r \n" +
            "\t\twhere r.date = :date and r.end_time > :startTime  and r.start_time < :endTime) \n" +
            "and gym_id not in \n" +
            "( select gym_id \n" +
            "\tfrom course \n" +
            "\twhere course.weekday = :weekday and course.end_time > :startTime  and course.start_time < :endTime)",
    nativeQuery = true)
   List<GymAndField> queryAvailableField(String date, String weekday, String startTime, String endTime);
}