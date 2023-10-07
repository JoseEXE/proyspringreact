import { useContext } from "react";
import { ClientContext } from "../../context/client/ClientContext";
import { AuthContext } from "../../auth/context/AuthContext";

export const ClientRows = ({ registre }) =>{

    const { handlerSelectRegistre } = useContext(ClientContext);
    const { login } = useContext(AuthContext);
    let option = "";


    return(<>
        <td> 
            <input className="form-check-input" type="radio" name="clientCheck" id="clientCheck" value={ registre.id }></input>
        </td>
        <td>{ registre.nom }</td>
        <td>{ registre.prenom }</td>
        <td className="text-center">{registre.tel} </td>
        {!login.isAdmin ||<>
           
            <td><button type="button" className="btn btn-warning btn-sm" onClick={() => handlerSelectRegistre(registre, option="edit") }><i className="bi bi-pencil"></i> Modifier</button></td>
            <td><button type="button" className="btn btn-success btn-sm" onClick={() => handlerSelectRegistre(registre, option="detail") }><i className="bi bi-zoom-in"></i> Détail</button></td>
            
              </>  
        }
    </>);
}