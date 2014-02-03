

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RhymeModel {
	
	public ArrayList<RhymeBank> getAllWords() {
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
		
		Transaction transaction = session.beginTransaction();
		Query allWordsQuery = session.createQuery("select u from RhymeBank as u order by u.id");
		
		List wordsList = allWordsQuery.list();
		
		ArrayList<RhymeBank> words = new ArrayList<RhymeBank>();
		Iterator<RhymeBank> iter = wordsList.iterator();
		while(iter.hasNext()) {
			words.add(iter.next());
		}
		
		transaction.commit();
		
		return words;
		
	}
	
	public ArrayList<RhymeBank> getAllRhymingWords(String rhymeScheme) {
Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
		
		Transaction transaction = session.beginTransaction();
		Query allWordsQuery = session.createQuery("select u from RhymeBank as u where u.rhymeType='" + rhymeScheme + "' order by u.id");
		
		List wordsList = allWordsQuery.list();
		
		ArrayList<RhymeBank> words = new ArrayList<RhymeBank>();
		Iterator<RhymeBank> iter = wordsList.iterator();
		while(iter.hasNext()) {
			words.add(iter.next());
		}
		
		transaction.commit();
		
		return words;
		
	}
	
}