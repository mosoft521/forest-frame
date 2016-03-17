import com.dempe.forest.manger.dao.UserDao;
import com.dempe.forest.manger.model.User;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.query.QueryResults;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/17
 * Time: 14:55
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class SimpleTest {

    @Resource
    private UserDao userDao;

    @Test
    public void test() {
        List<User> users = userDao.createQuery().asList();

        for (User user : users) {
            String uid = user.getUid();
            System.out.println(uid);
            User user1 = userDao.get(uid);
            System.out.println(user1);
        }
        User user = userDao.get("56e933457064e07e56533831");
        System.out.println(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setUid("56e934c6706458a46cc9efba");
        user.setRoleId(5);
//        user.setName("test");
        userDao.updateUser(user);
        User user1 = userDao.get(new ObjectId("56e934c6706458a46cc9efba"));
        System.out.println(user1);
    }
}
