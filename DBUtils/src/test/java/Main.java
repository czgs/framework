import com.dbutils.handler.ResultSetHandler;
import com.dbutils.handler.SQLExecutor;
import com.dbutils.handler.handler.BeanHandler;
import com.dbutils.model.entity.UserInfo;
import com.dbutils.utils.DBUtil;

import java.sql.SQLException;

/**
 * Created by Chen on 2017/12/7.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        SQLExecutor sqlExecutor = new SQLExecutor(DBUtil.getConnection());
        ResultSetHandler handler =  new BeanHandler(UserInfo.class);
        UserInfo userInfo = (UserInfo) sqlExecutor.executeQuery("select * from DB_USER where DB_NAME =?",handler, (Object) "feng");
        System.out.println(userInfo);
    }
}
