package cn.itcast.test;

import cn.itcast.dao.AccountDao;
import cn.itcast.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestMyBatis {
    /**
     * 测试查询
     * @throws Exception
     */
    @Test
    public void run1() throws Exception {
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取代理对象
        AccountDao dao = sqlSession.getMapper(AccountDao.class);
        //查询所有数据
        List<Account> accounts = dao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
        //关闭资源
        sqlSession.close();
        in.close();
    }


    /**
     * 测试保存
     * @throws Exception
     */
    @Test
    public void run2() throws Exception {
        Account account = new Account();
        account.setName("Helen");
        account.setMoney(400d);
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取代理对象
        AccountDao dao = sqlSession.getMapper(AccountDao.class);
        //保存数据
        dao.saveAccount(account);
        //提交事务
        sqlSession.commit();
        //关闭资源
        sqlSession.close();
        in.close();
    }
}
