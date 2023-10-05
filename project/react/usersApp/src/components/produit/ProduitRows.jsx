import { useContext } from "react";
import { ProduitContext } from "../../context/produit/ProduitContext";
import { AuthContext } from "../../auth/context/AuthContext";

export const ProduitRows = ({ registre }) =>{
    const { handlerSelectRegistre, registresCatProd } = useContext(ProduitContext);
    const { login } = useContext(AuthContext);
    let option = "";

    return(<>
        <td>{ registre.id }</td>
            <td>{ registre.nom }</td>
            <td>{ registre.description }</td>
            <td className="text-center">{ 
            registresCatProd.map(registreCatProduct => {
              console.log("registreCatProduct: "+ registreCatProduct);
              if( registre.catProduit == registreCatProduct.id){
                return ( registreCatProduct?.nom );
              }
            })  
              
            }
            </td>
            <td className="text-center">{ registre.type_plat }</td>
            {!login.isAdmin ||<>
           
            <td><button type="button" className="btn btn-warning btn-sm" onClick={() => handlerSelectRegistre(registre, option="edit") }><i className="bi bi-pencil"></i> Modifier</button></td>
            <td><button type="button" className="btn btn-success btn-sm" onClick={() => handlerSelectRegistre(registre, option="detail") }><i className="bi bi-zoom-in"></i> Détail</button></td>
            
              </>  
            }
    
    
        
        </>);
}