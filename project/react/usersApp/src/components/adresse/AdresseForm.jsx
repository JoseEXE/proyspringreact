import { useContext, useEffect, useState } from "react";
import { AdresseContext } from "../../context/adresse/AdresseContext";

export const AdresseForm = ({ selectRegistre, handerFormClose, optionForm }) => {

    const { initEstructure, errors, handlerAddRegistre, clientActive } = useContext(AdresseContext); //userEstructure, handlerAddUser
    const [useForm, setUseForm] = useState(initEstructure);
    const { id, client, rue, cod_postal, ville, complement, statut  } = useForm;
    const [checked, setChecked]= useState(useForm.statut);

    const onSubmit = (event) =>{
        event.preventDefault();

        useForm.client = "";
        useForm.client =  {
               "id": clientActive,
           };

           
        handlerAddRegistre(useForm);
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

      console.log("=======================clientActive=====================: "+clientActive);

  return(optionForm === "edit" || optionForm === "new"? 
    <>
    <form onSubmit={ onSubmit }>

          <div className="row">
          </div>
          <div className="row align-items-center">
            <div className="col mt-1">
              <label className="col-sm-6 control-label small">rue: </label>
              <input type="text" className="form-control" placeholder="Rue" name="rue" value={rue} onChange={ onInputChange } />
              <p className="text-danger small">{errors?.rue}</p>
            </div>
          </div>

          <input type="hidden" className="form-control" name="client" value={clientActive}  />
      
          
          <div className="row align-items-center mt-1">
            <div className="col">
              <label className="col-sm-6 control-label small">Cod Postal: </label>
              <input type="text" className="form-control" placeholder="Code Produit" name="cod_postal" value={cod_postal}  onChange={ onInputChange }/>
              <p className="text-danger small ">{errors?.cod_postal}</p>
            </div>
            <div className="col">
              <label className="col-sm-6 control-label small">Ville: </label>
              <input type="text" className="form-control" placeholder="Ville" name="ville" value={ville}  onChange={ onInputChange } />
              <p className="text-danger small">{errors?.ville}  </p>
            </div>
          </div>

          <div className="row align-items-center">
            <div className="col mt-1">
              <label className="col-sm-6 control-label small">Complement: </label>
              <input type="text" className="form-control" placeholder="Complement" name="complement" value={complement} onChange={ onInputChange } />
              <p className="text-danger small">{errors?.complement}</p>
            </div>
          </div>

          
          
          
          <div className="row justify-content-start mt-1">
            <div className="col">
              <div className="form-check">
              <input type="checkbox" name="statut"  className="form-check-input" checked={statut}    onChange={ onCheckboxChange } /> {(statut == true) ? 'Adresse Active' : 'Adresse Inactif'}
       
              </div>

    
              <hr/>
              <button className="btn btn-primary btn-sm" type="submit"><i className="bi bi-save-fill"></i> &nbsp;
                {id <= 0 ? 'Créer Nouvelle Adresse' : 'Modifier Catègorie Adresse'}
              </button>
        {!handerFormClose || <button className="btn btn-primary mx-2 btn-sm" type="button" onClick={ onCloseForm }><i className="bi bi-box-arrow-in-right"></i> &nbsp; Fermer</button>}
            </div>



          </div>

    </form>
    </>
    :
    <>
    <CatProduitFormDetail useForm={useForm} handerFormClose={handerFormClose} onCloseForm={onCloseForm} />   

    
    </>);
}