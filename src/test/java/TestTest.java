import com.alan344.mapper.UserMapper;
import com.alan344.model.UserEntity;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author AlanSun
 * @date 2020/3/13 15:39
 */
@RunWith(JUnit4.class)
public class TestTest {

    private UserMapper mapper;

    @Before
    public void pre() throws IOException {
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
        mapper = sqlSession.getMapper(UserMapper.class);

    }

    @Test
    public void testCursor() {
        try (Cursor<UserEntity> users = mapper.getUsers(new RowBounds(0, 100))) {
            System.out.println(users.isConsumed());
            System.out.println(users.isOpen());
            AtomicBoolean falg = new AtomicBoolean(true);
            Iterator<UserEntity> iterator = users.iterator();
            while (iterator.hasNext()) {
                UserEntity userEntity = iterator.next();
                if(falg.get() && users.isOpen()){
                    System.out.println(users.isOpen());
                    falg.set(false);
                }
                System.out.println(userEntity.getName());
            }
            System.out.println(users.isConsumed());
            System.out.println(users.isOpen());
            int i = 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
