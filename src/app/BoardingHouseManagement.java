package app;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import config.HibernateUtil;
import entity.Employee;

public class BoardingHouseManagement {

	static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public static void main(String[] args) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			if (!session.getTransaction().isActive())
				session.beginTransaction();

			// Thực hiện truy vấn và lấy dữ liệu
			List<Employee> employees = session.createQuery("FROM Employee", Employee.class).list();

			// Hiển thị dữ liệu
			for (Employee employee : employees) {
				System.out.println(employee);
			}

			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (session != null && session.isOpen()) {
				session.getTransaction().rollback();
			}
			System.err.println("EmployeeManagement with error: " + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
}
