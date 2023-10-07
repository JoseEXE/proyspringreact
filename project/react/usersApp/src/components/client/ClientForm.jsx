import { useContext, useEffect, useState } from "react";
import { ClientContext } from "../../context/client/ClientContext";
import { AuthContext } from "../../auth/context/AuthContext";
import { ClientFormDetail } from "./ClientFormDetail";

export const ClientForm = ({ selectRegistre, optionForm }) =>{

    const { initEstructure, errors, handlerAddRegistre, handerFormClose } = useContext(ClientContext); 
    const { login } = useContext(AuthContext);
    const [useForm, setUseForm] = useState(initEstructure);
    const { id, nom, prenom, tel, statut  } = useForm;
    const [checked, setChecked]= useState(useForm.statut);
    const [errorsf, setErrorsf] = useState({});

    const validation = (useForm) =>{
        const errorsf = {}
        const num_pattern = new RegExp(/^\d+(?:[.,]\d+)?$/);
    

        if(useForm.nom == "" || useForm.nom == undefined){
            errorsf.nom = "Le champ nom est requis ! ";
        }else{
            errorsf.nom = "";
        }
        if(useForm.prenom == "" || useForm.prenom == undefined ){
            errorsf.prenom = "Le champ prenom est requis ! ";
        }else{
            errorsf.prenom = "";
        }
        if(useForm.tel == 0|| useForm.tel == "" || useForm.tel == undefined){
            errorsf.tel = "Le champ tel est requis ! ";
        }

        else if(!num_pattern.test(useForm.tel)){
            errorsf.tel = "Le format tel n'est pas correct (Uniquement des numéros)!.";
        
        }else{
            errorsf.tel = "";
        }
        return errorsf;
    }

    useEffect(() => {
  
        setUseForm({ 
            ...selectRegistre
        });
      
    },[selectRegistre])

    const onInputChange = ({ target }) => {
        const { name, value } = target;
        
        setUseForm({
                ...useForm,
                [name]: value,
            })        
    }

    const onCheckboxChange = () =>{
        setChecked(!checked);
        setUseForm({
            ...useForm,
            statut: checked,
        
        })
    }
    const onCloseForm = () => {
        handerFormClose();
        setUseForm(initEstructure);
      
    }

    function onSubmit(event) {
        event.preventDefault();
        setErrorsf(validation(useForm));
         
        if(errorsf.tel == ''){
      
          handlerAddRegistre(useForm);
    
        }
    }

    
    console.log("optionForm = :"+optionForm );

    return(optionForm === "edit" || optionForm === "new"? 
    <>

        <form onSubmit={ onSubmit }>


            <div className="row">
            </div>
            <div className="row align-items-center">
                <div className="col mt-1">
                    <label className="col-sm-6 control-label small">Nom: </label>
                    <input type="text" className="form-control" placeholder="Nom" name="nom" value={nom} onChange={ onInputChange } />
                    <p className="text-danger small">{errors?.nom} {errorsf?.nom}</p>
                </div>
            </div>

            <div className="row align-items-center">
                <div className="col mt-1">
                    <label className="col-sm-6 control-label small">Prenom: </label>
                    <input type="text" className="form-control" placeholder="Prenom" name="prenom" value={prenom} onChange={ onInputChange } />
                    <p className="text-danger small">{errors?.prenom} {errorsf?.prenom}</p>
                </div>
            </div>

            <div className="row align-items-center">
                <div className="col mt-1">
                    <label className="col-sm-6 control-label small">Tel: </label>
                    <input type="text" className="form-control" placeholder="Numéro téléphone" name="tel" value={tel} onChange={ onInputChange } />
                    <p className="text-danger small">{errors?.tel} {errorsf?.tel}</p>
                </div>
            </div>


            <div className="row justify-content-start mt-1">
                <div className="col">
                    <div className="form-check">
                        <input type="checkbox" name="statut"  className="form-check-input" checked={statut}    onChange={ onCheckboxChange } /> {(statut == true) ? ' Client Active' : ' Client Inactif'}
                    </div>
                    <hr/>
                    <button className="btn btn-primary btn-sm" type="submit"><i className="bi bi-save-fill"></i> &nbsp;
                        {id <= 0 ? 'Créer nouvelle  Client' : 'Modifier Client'}
                    </button>
                    {!handerFormClose || <button className="btn btn-primary mx-2 btn-sm" type="button" onClick={ onCloseForm }><i className="bi bi-box-arrow-in-right"></i> &nbsp; Fermer</button>}
                </div>
            </div>

        </form>
    </>
    :
    <>
    <ClientFormDetail useForm={useForm} handerFormClose={handerFormClose} onCloseForm={onCloseForm} />   

    </>);
};