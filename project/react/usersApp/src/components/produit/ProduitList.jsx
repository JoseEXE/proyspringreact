import { useContext } from "react";
import { ProduitContext } from "../../context/produit/ProduitContext";
import { AuthContext } from "../../auth/context/AuthContext";
import { ProduitRows } from "./ProduitRows";

export const ProduitList = () => {
    const { registres, registresCatProd } = useContext(ProduitContext);
    const { paginator } = useContext(ProduitContext);
    const { login } = useContext(AuthContext);    

    return(
        <table className="table table-hover table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Description</th>
                        <th className="text-center">Catégorie</th>
                        <th className="text-center">Type Plat</th>
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
                <ProduitRows registre={ registre } />
            </tr>

))
}
</tbody>
</table> );
}