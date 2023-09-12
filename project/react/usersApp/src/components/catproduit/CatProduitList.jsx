import { useContext } from "react";
import { AuthContext } from "../../auth/context/AuthContext";
import { CatProduitContext } from "../../context/catproduit/CatProduitContext";
import { CatProduitRows } from "./CatProduitRows";

export const CatProduitList = () =>{
    const { registres } = useContext(CatProduitContext);
    const { paginator } = useContext(CatProduitContext);
    const { login } = useContext(AuthContext);    

    return(
        <table className="table table-hover table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Description</th>

                        {!login.isAdmin ||<>
                            <th></th>
                            <th></th>
                        </>
                        }
            
                    </tr>
                </thead>
                <tbody>
                {

              
        registres.map(registre => (
            <tr key={ registre.id   }>
                <CatProduitRows registre={ registre } />
            </tr>

))
}
</tbody>
</table> );

}