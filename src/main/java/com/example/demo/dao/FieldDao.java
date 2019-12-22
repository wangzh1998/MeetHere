package com.example.demo.dao;

import com.example.demo.entity.Field;
import com.example.demo.vo.GymAndField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface FieldDao extends JpaRepository<Field,Integer> {
    @Query(value = "select new com.example.demo.vo.GymAndField" +
            "(g.gymId,g.gymName,g.startTime,g.endTime,f.fieldId,f.fieldName) " +
            "from Field f join Gym g " +
            "on f.gymId = ?1 and f.fieldId = ?2")
    GymAndField queryFieldById(int gid, int fid);


    @Query(value="select gym_id,gym_name,start_time,end_time,field_id,field_name " +
            "from field natural join \n" +
            "(select gym_id" +
            "from gym" +
            "where start_time <= ?3 and end_time >= ?4) as b" +
            "natural join " +
            " ( \tselect gym_id , field_id\n" +
            "      \tfrom field \n" +
            "      \twhere (gym_id , field_id) \n" +
            "        not in (\tselect gym_id ,field_id\n" +
            "        \t\t\tfrom reserve r \n" +
            "                \twhere r.endtime >= ?3 and r.starttime <= ?4 and r.date = ?1\n" +
            "                )\n" +
            "  ) as a \n" +
            "where gym_id \n" +
            "     not in ( select gym_id\n" +
            "     \t\t  from course\n" +
            "              where course.weekday = ?2 and course.endtime >= ?3 and course.starttime <= ?4)",nativeQuery = true)
    List<GymAndField> queryAvailableField(String date, String weekday, String startTime, String endTime);
}