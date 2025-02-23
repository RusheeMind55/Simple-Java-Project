package com.tka.OrganizationWorkFlow.dao;

import java.util.List;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tka.OrganizationWorkFlow.entity.Country;
import com.tka.OrganizationWorkFlow.entity.Employee;

@Repository
public class MainDao {

	@Autowired
	SessionFactory factory;

	public String addCountry(Country c) {
		Session session = null;
		Transaction tx = null;
		String msg = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.persist(c);
			tx.commit();
			msg = "Country Addedd Successfully...";
		} catch (Exception e) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {

			if (session != null) {
				session.close();
			}
		}

		return msg;

	}

	public String updateCountry(Country c, int id) {

		Session session = null;
		Transaction tx = null;
		String msg = null;

		try {

			session = factory.openSession();
			tx = session.beginTransaction();
			Country country = session.get(Country.class, id);
			country.setCname(c.getCname());
			session.merge(country);
			tx.commit();
			msg = "Country is Updated";

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}

			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return msg;
	}

	public String deleteCountry(int cid) {
		Session session = null;
		Transaction tx = null;
		String msg = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();

			Country country = session.get(Country.class, cid);
			session.remove(country);
			tx.commit();

			msg = "Country is Deleted..";

		} catch (Exception e) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return msg;
	}

	public List<Country> getAllCountry() {
		Session session = null;
		Transaction tx = null;
		List<Country> list = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			String hqlQuery = "from Country";

			Query<Country> query = session.createQuery(hqlQuery, Country.class);
			list = query.list();
			tx.commit();

		} catch (Exception e) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;
	}

	public Country getparticularCountryById(int cid) {
		Session session = null;
		Transaction tx = null;
		Country country = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();

			country = session.get(Country.class, cid);
			tx.commit();

		} catch (Exception e) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return country;
	}

	public String addEmployee(Employee emp) {
		Session session = null;
		Transaction tx = null;
		String msg = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();

			session.persist(emp);
			tx.commit();
			msg = "Employee Added Successfuly";

		} catch (Exception e) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return msg;
	}

	public String updateEmployee(int id, Employee emp) {
		Session session = null;
		Transaction tx = null;
		String msg = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();

			Employee employee = session.get(Employee.class, id);
			employee.setName(emp.getName());
			employee.setMobileno(emp.getMobileno());
			employee.setEmailid(emp.getEmailid());
			employee.setSalary(emp.getSalary());
			employee.setStatus(emp.getStatus());
			employee.setDepartment(emp.getDepartment());
			employee.setCreatedBy(emp.getCreatedBy());
			employee.setCeratedDate(emp.getCeratedDate());
			employee.setUpdatedBy(emp.getUpdatedBy());
			employee.setUpdatedDate(emp.getUpdatedDate());
			employee.setCountry(emp.getCountry());
			session.merge(employee);
			tx.commit();
			msg = "Employee Record is Updatated....";

		} catch (Exception e) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return msg;

	}

	public String deleteEmployee(int id) {

		Session session = null;
		Transaction tx = null;
		String msg = null;

		try {

			session = factory.openSession();
			tx = session.beginTransaction();

			Employee emp = session.get(Employee.class, id);
			session.remove(emp);
			tx.commit();
			msg = "Employee is Deleted...";

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();

		} finally {

			if (session != null) {
				session.close();
			}

		}
		return msg;
	}

	public List<Employee> getAllEmployee() {
		Session session = null;
		Transaction tx = null;
		List<Employee> list = null;
		try {

			session = factory.openSession();
			tx = session.beginTransaction();
			String hqlquery = "from Employee";
			Query<Employee> query = session.createQuery(hqlquery, Employee.class);
			list = query.list();
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();

		} finally {

			if (session != null) {
				session.close();
			}

		}

		return list;
	}

	public Employee getParticularById(int id) {
		Session session = null;
		Transaction tx = null;
		Employee emp = null;
		try {

			session = factory.openSession();
			tx = session.beginTransaction();
			emp = session.get(Employee.class, id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();

		} finally {

			if (session != null) {
				session.close();
			}

		}
		return emp;

	}

	public Employee login(Employee e) {
		Session session = null;
		Transaction tx = null;
		Employee emp = null;
		try {

			session = factory.openSession();
			tx = session.beginTransaction();
			String hqlQuery = "from Employee where emailid=:emailid and mobileno=:mobileno";

			Query<Employee> query = session.createQuery(hqlQuery, Employee.class);
			query.setParameter("emailid", e.getEmailid());
			query.setParameter("mobileno", e.getMobileno());

			emp = query.uniqueResult();
			tx.commit();

		} catch (Exception e1) {
			if (tx != null) {
				tx.rollback();
			}
			e1.printStackTrace();

		} finally {

			if (session != null) {
				session.close();
			}

		}
		return emp;

	}

	public String updateStatus(int id) {

		String msg = null;
		Session session = null;
		Transaction tx = null;

		try {

			session = factory.openSession();
			tx = session.beginTransaction();
			
			Employee emp= session.get(Employee.class,id);
			
			if(Objects.isNull(emp)) {
				msg="Record is not found";
			}else {
				
				if(emp.getStatus().equalsIgnoreCase("suspend")) {
					msg="Status is not updated due to suspend";
				}else {
					String status="active".equalsIgnoreCase(emp.getStatus())?"inactive":"active";
					emp.setStatus(status);
					session.merge(emp);
					msg="Status is updated";
				}
				
			}
			tx.commit();
			

		} catch (Exception e1) {
			if (tx != null) {
				tx.rollback();
			}
			e1.printStackTrace();

		} finally {

			if (session != null) {
				session.close();
			}

		}

		return msg;
	}

}
