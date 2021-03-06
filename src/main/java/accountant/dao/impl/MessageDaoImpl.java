package accountant.dao.impl;

import java.util.HashSet;
import java.util.Set;

import accountant.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import accountant.dao.AbstractDao;
import accountant.dao.MessageDao;
import accountant.model.Message;
import accountant.model.State;

@Repository("messageDao")
public class MessageDaoImpl extends AbstractDao<Integer, Message> implements MessageDao {

	@Override
	public Message findById(int id) {
		return getByKey(id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Message> findIncoming(User user) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("from", user));
		criteria.add(Restrictions.eq("state", State.ACTIVE.toString()));
		return new HashSet<Message>(criteria.list());
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Message> findOutcoming(User user) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("to", user));
		criteria.add(Restrictions.eq("state", State.ACTIVE.toString()));
		return new HashSet<Message>(criteria.list());
	}

	@Override
	public void markDelete(int messageId) {
		Message message = findById(messageId);
		message.setState(State.DELETED.toString());
		update(message);
	}

}
