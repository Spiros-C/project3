package org.project.morebananas.dao;

import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.project.morebananas.entities.Users;
import org.project.morebananas.dao.IUsersDao;
import org.springframework.stereotype.Repository;

@Repository("iUsersDao")
public class UsersDao extends AbstractDao<Integer, Users> implements IUsersDao{    
	//An alternative to Hibernate.initialize()
	protected void initializeCollection(Collection<?> collection) {
	    if(collection == null) {
	        return;
	    }
	    collection.iterator().hasNext();
	}
//    @Override
//    public Users findById(int userId) {
//        Users user = getByKey(userId);
//        if (user != null){
//            return user;
//        }
//        return null;
//    }
    
        @Override
    	public Users findById(int userId) {
		Users user = getByKey(userId);
		if(user!=null){
			initializeCollection(user.getUserProfiles());
		}
		return user; //null
	}

    @Override
//    public List<Users> findAllUsers() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//    
//      public List<Users> findAllUsers() {
//         Criteria criteria = createEntityCriteria();
//         //return((List<Users>)criteria.list());
//         return(criteria.list());
//                 
//    }
   	@SuppressWarnings("unchecked")
    public List<Users> findAllUsers() {
        List<Users> users = getEntityManager()
                .createQuery("SELECT u FROM Users u ORDER BY u.userName ASC")
                .getResultList();
        return users;
    }

    
    @Override
    public void save(Users user) {
        persist(user);
    }
    
//    @Override
//    public void booleanUpdate(Users user) {
//        Users tempUser = findById(user.getUserId());
//        if (tempUser != null) {
//            tempUser.setUserName(user.getUserName());
//            tempUser.setEmail(user.getEmail());
//            tempUser.setPassword(user.getPassword());
//            tempUser.setCreditLimit(user.getCreditLimit());
//            return(save(tempUser));
//        } else return (false);       
//    }
    
    
    @Override
    public void deleteUserByUserId(int userId) {
        Users user = (Users) getEntityManager()
                .createQuery("SELECT u FROM Users u WHERE u.userId LIKE :userId")
                .setParameter("userId", userId)
                .getSingleResult();
        delete(user);
    }
    @Override
    public boolean booleanDeleteUserByUserId(int userId) {
        Users tempUser = findById(userId);
        if (tempUser != null) {
            delete(tempUser);
            return (true);
        }
        return (false);
    }
}
