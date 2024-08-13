package store.badminton.BadmintonStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.badminton.BadmintonStore.entities.Picture;

@Repository
public interface PictureRepo extends JpaRepository<Picture, Long> {
}
