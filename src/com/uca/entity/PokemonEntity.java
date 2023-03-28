
public class PokemonEntity {
    private String name;
    private int id;
    private int pokedexId;
    private int level;
    private UserEntity user; // Composition
    private ExchangeEntity exchange;
    private String sprite;
    private int userid;

    public PokemonEntity(String name, int pokedexId, UserEntity user, String sprite) {
        this.name = name;
        this.pokedexId = pokedexId;
        this.user = user;
        this.sprite = sprite;
        this.level = 1;
    }


    


}