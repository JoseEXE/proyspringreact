import { useContext } from "react";
import { AdresseContext } from "../../context/adresse/AdresseContext";
import { AuthContext } from "../../auth/context/AuthContext";

export const AdresseRows = ({ registre }) => {

    const { handlerSelectRegistre, handlerSelectAdresse } = useContext(AdresseContext);
    const { login } = useContext(AuthContext);
    let option = "";


    return(<>
      <td><input className="form-check-input" type="radio" name="adresseCheck" id="adresseCheck" value={ registre.id } onClick={() => handlerSelectAdresse(registre.id) }></input></td>
             <td>{ registre.rue }</td>
            <td>{ registre.cod_postal }</td>
            <td>{ registre.ville }</td>
            <td>{ registre.complement}</td>
            {!login.isAdmin ||<>
           
            <td><button type="button" className="btn btn-warning btn-sm" onClick={() => handlerSelectRegistre(registre, option="edit") }><i className="bi bi-pencil"></i> Modifier</button></td>
            <td><button type="button" className="btn btn-success btn-sm" onClick={() => handlerSelectRegistre(registre, option="detail") }><i className="bi bi-zoom-in"></i> Détail</button></td>
              </>  
            }


    </>);
}