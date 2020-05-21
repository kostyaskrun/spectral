package com.spectral.demoapplication.model.specification;

import com.spectral.demoapplication.model.entity.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

/**
 * User specification for filter purpose, be aware that you can use
 * {@link UserSpecification#findUserBySearchParameters(String, String, String, String, LocalDateTime, LocalDateTime)}
 * with null parameter in any field. If you passed null in all fields, will be fetched all data.
 */
public final class UserSpecification {

    public static Specification<User> findUserBySearchParameters(String name,
                                                                 String email,
                                                                 String countryName,
                                                                 String roleName,
                                                                 LocalDateTime dateFrom,
                                                                 LocalDateTime dateTo) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (nonNull(name)) {
                predicates.add(cb.equal(root.get("name"), name));
            }
            if (nonNull(email)) {
                predicates.add(cb.equal(root.get("email"), email));
            }
            if (nonNull(countryName)) {
                predicates.add(cb.equal(root.get("country").get("name"), countryName));
            }
            if (nonNull(roleName)) {
                Join join = root.join("roles");
                return join.get("name").in(roleName);
            }
            if (nonNull(dateFrom)) {
                predicates.add(cb.greaterThan(root.get("createdAt"), dateFrom));
            }
            if (nonNull(dateTo)) {
                predicates.add(cb.lessThan(root.get("createdAt"), dateTo));
            }
            return cb.and(predicates.toArray(new Predicate[]{}));
        };
    }
}
