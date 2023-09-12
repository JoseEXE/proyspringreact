import { useContext } from "react";
import { CatProduitContext } from "../../context/catproduit/CatProduitContext";
import { AuthContext } from "../../auth/context/AuthContext";

export const CatProduitRows = ({ registre }) =>{

    const { handlerSelectRegistre } = useContext(CatProduitContext);
    const { login } = useContext(AuthContext);
    let option = "";

    return(<>
        <td>{ registre.id }</td>
            <td>{ registre.nom }</td>
            <td>{ registre.description }</td>
            {!login.isAdmin ||<>
           
            <td><button type="button" className="btn btn-warning btn-sm" onClick={() => handlerSelectRegistre(registre, option="edit") }><i className="bi bi-pencil"></i> Modifier</button></td>
            <td><button type="button" className="btn btn-success btn-sm" onClick={() => handlerSelectRegistre(registre, option="detail") }><i className="bi bi-zoom-in"></i> Détail</button></td>
            
              </>  
            }
    
    
        
        </>);
}