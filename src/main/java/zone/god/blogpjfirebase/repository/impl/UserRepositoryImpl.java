package zone.god.blogpjfirebase.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import zone.god.blogpjfirebase.model.User;
import zone.god.blogpjfirebase.repository.UserRepository;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final String COLLECTION_NAME = "users";

    @Override
    public boolean saveUser(User user) throws ExecutionException, InterruptedException {
        if (checkIfUserExist(user.getUsername())) return false;
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> userData = objectMapper.convertValue(user, Map.class);
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture = firestore.collection(COLLECTION_NAME).document("" + user.getId()).set(userData);
        return apiFuture.get().getUpdateTime() != null;
    }

    @Override
    public boolean deleteUser(String username, String password) {
        return false;
    }

    @Override
    public User getUser(String username, String password) {
        return null;
    }

    @Override
    public boolean checkIfUserExist(String username) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionReference = firestore.collection(COLLECTION_NAME);
        Query query = collectionReference.whereEqualTo("username", username);
        ApiFuture<QuerySnapshot> snapshotApiFuture = query.get();
        return snapshotApiFuture.get().getDocuments().size() != 0;
    }
}
