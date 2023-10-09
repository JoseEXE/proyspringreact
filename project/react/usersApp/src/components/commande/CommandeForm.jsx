import { useContext, useState } from "react";

import { ClientContext } from "../../context/client/ClientContext";
import { AdresseContext } from "../../context/adresse/AdresseContext";
import { AuthContext } from "../../auth/context/AuthContext";
import { CommandeContext } from "../../context/commande/CommandeContext";


export const CommandeForm = () =>{

    const { initEstructure, errors, handlerAddRegistre, handerFormClose, initTypePaiment  } = useContext(CommandeContext);

    const { VisibleregistresAdresseClient } = useContext(ClientContext);

    const { adresseActive } = useContext(AdresseContext);

    const { login } = useContext(AuthContext);

    const [useForm, setUseForm] = useState(initEstructure);
    const { id, client, user, adresse, commentaire, totalHl, total, type_paiement, etat, createdOn  } = useForm;
    const [errorsf, setErrorsf] = useState({});
    
    const onInputChange = ({ target }) => {
        const { name, value } = target;
        
        setUseForm({
                ...useForm,
                [name]: value,
            })
            
        }

        const validation = (useForm) =>{
            const errorsf = {}
        
            if(useForm.type_paiement == 0){
                errorsf.type_paiement = "Vous devez sélectionner un Type de paiement ! ";
            }else{
                errorsf.type_paiement = "";
            }
            return errorsf;
        }

          

    function onSubmit(event) {
        event.preventDefault();
        setErrorsf(validation(useForm));

        if(errorsf.type_paiement == ''){
            useForm.etat = "0";

            let client = useForm.client;
            useForm.client = "";
            useForm.client = { "id": VisibleregistresAdresseClient};
    
            let user = useForm.user;
            useForm.user = "";
            useForm.user = { "id": login.user.id };
    
            let adresse = useForm.adresse;
            useForm.adresse = "";
            useForm.adresse = { "id": adresseActive };
    
            handlerAddRegistre(useForm);
        }
     
    }



    return(<>
     {/* { !visibleForm || 
        <CommandeModalForm />
        } */}

    <div className="container my-1 border p-1">
        <div className="gap-2 w-50">
            <form onSubmit={ onSubmit }>
               
                <select className="form-select " aria-label="Default select example" name="type_paiement"  onChange={ onInputChange }>
                    {type_paiement == "" ? <option value="0" selected>Type de paiement</option> : <option value={type_paiement} selected>{type_paiement}</option> }

                    {initTypePaiment.map(tPaiment =>{
                    if(tPaiment != type_paiement){
                        return (<option value={tPaiment}>{tPaiment}</option>);
                    }
                    })}

                </select>

                <p className="text-danger small">{errorsf?.type_paiement}</p>
                <label className="col-sm-6 control-label">Commentaire: </label>
                <textarea  className="form-control" id="commentaire" name="commentaire" rows="3" cols="5" > </textarea>

                <br/><br/>
                    <button className="btn btn-primary " type="submit"><i class="bi bi-arrow-right-circle-fill"></i> &nbsp; Continue commande </button>  
                
                <br/><br/><br/><br/><br/><br/>

            </form>
        </div>
    </div>

    
    </>);
}