import { useContext } from "react";
import { AuthContext } from "../../auth/context/AuthContext";
import { NavLink } from "react-router-dom";
import { EtablissementContext } from "../../context/etablissement/EtablissementContext";
import dayjs from "dayjs";

export const EtablissementRows = ({ etablissement }) =>{


    const { handlerSelectEtablissement} = useContext(EtablissementContext);
    const { login } = useContext(AuthContext);
    let option = "";

    return(<>
    <td>{ etablissement.id }</td>
        <td>{ etablissement.nom }</td>
        <td>{ etablissement.rue }</td>
        <td>{ etablissement.cod_postal }</td>
        <td>{ dayjs(etablissement?.createdOn).format("DD/MM/YYYY") }</td>
        {!login.isAdmin ||<>
       
        <td><button type="button" className="btn btn-warning btn-sm" onClick={() => handlerSelectEtablissement(etablissement, option="edit") }><i className="bi bi-pencil"></i> Modifier</button></td>
        <td><button type="button" className="btn btn-success btn-sm" onClick={() => handlerSelectEtablissement(etablissement, option="detail") }><i className="bi bi-zoom-in"></i> Détail</button></td>
        
          </>  
        }


    
    </>);
}