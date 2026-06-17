package org.example.Repository;

import org.example.ItemGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ItemGroupReadRepository extends JpaRepository<ItemGroup, Long> {
    List<ItemGroup> findItemGroupByName(String name);
}
