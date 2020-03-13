package com.alan344;

import com.alan344.mapper.UserMapper;
import com.alan344.model.UserEntity;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

/**
 * @author AlanSun
 * @date 2019/12/23 13:09
 */
public class Main {
    public static void main(String[] args) {
        try {
            // 基本mybatis环境
            // 1.定义mybatis_config文件地址
            String resources = "mybatis_config.xml";
            // 2.获取InputStreamReaderIo流
            Reader reader = Resources.getResourceAsReader(resources);
            // 3.获取SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            // 4.获取Session
            SqlSession sqlSession = sqlSessionFactory.openSession();
            // 5.操作Mapper接口
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            Cursor<UserEntity> users = mapper.getUsers(new RowBounds(0, 2));

            users.forEach(userEntity -> {
//                System.out.println(users.isOpen());
                System.out.println(userEntity.getName());
            });
            System.out.println(users.isConsumed());
            System.out.println(users.isOpen());
//            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//            List<Integer> integers = Arrays.asList(1, 11, 12);
//            Map<String, UserEntity> keyMap1 = mapper.getKeyMap(new HashSet<>(integers));
            int i = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
