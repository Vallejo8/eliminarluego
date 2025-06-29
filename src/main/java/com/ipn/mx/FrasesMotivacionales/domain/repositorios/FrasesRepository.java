package com.ipn.mx.FrasesMotivacionales.domain.repositorios;

import com.ipn.mx.FrasesMotivacionales.domain.entidades.Frases;
import org.springframework.data.repository.CrudRepository;

public interface FrasesRepository extends CrudRepository<Frases, Long> {
    // public voud save (Frases f){
    //     Session s = HibernateUtil.getSessionFactory().openSession();
    //     Transaction t = s.getTransaction();
    //     t.begin();
    //     s.save(f);
    //     t.commit();
    //     }

}
