package org.example.Repository;

import org.example.ItemGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemGroupWriteRepository extends JpaRepository<ItemGroup, Long> {
    Optional<ItemGroup> findByPublicId(String publicId);
}
