package org.example.Repository;

import org.example.ItemGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ItemGroupRepository extends JpaRepository<ItemGroup, Long> {
}
