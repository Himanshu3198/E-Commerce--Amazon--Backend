package Hs.Ecommerce.IdentityAccessManagement;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role",unique = true)
    @Enumerated(EnumType.STRING)
    private RoleType name;

    public Long getId() { return id; }
    public RoleType getName() { return name; }
    public RoleEntity setName(RoleType name) {
        this.name = name;
        return this;
    }
}
