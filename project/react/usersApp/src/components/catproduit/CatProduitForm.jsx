import { useContext, useEffect, useState } from "react";
import { CatProduitContext } from "../../context/catproduit/CatProduitContext";
import { CatProduitFormDetail } from "./CatProduitFromDetail";

export const CatProduitForm = ({ selectRegistre, handerFormClose, optionForm }) =>{

  const { initEstructure, errors, handlerAddRegistre } = useContext(CatProduitContext); //userEstructure, handlerAddUser
  const [useForm, setUseForm] = useState(initEstructure);
  const { id, nom, description, statut } = useForm;
  const [checked, setChecked]= useState(useForm.statut);

  const onSubmit = (event) =>{
    event.preventDefault();
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

    return(optionForm === "edit" || optionForm === "new"? 
    <>
    <form onSubmit={ onSubmit }>

          <div className="row">
          </div>
          <div className="row align-items-center">
            <div className="col mt-1">
              <label className="col-sm-6 control-label small">Nom: </label>
              <input type="text" className="form-control" placeholder="Nom" name="nom" value={nom} onChange={ onInputChange } />
              <p className="text-danger small">{errors?.nom}</p>
            </div>
          </div>
          <div className="row align-items-center mt-1">
            <div className="col">
              <label className="col-sm-6 control-label small">Description: </label>
              <input type="text" className="form-control" placeholder="Description" name="description" value={description}  onChange={ onInputChange }/>
              <p className="text-danger small">{errors?.siret}</p>
            </div>
          </div>
          
          
          
          <div className="row justify-content-start mt-1">
            <div className="col">
              <div className="form-check">
              <input type="checkbox" name="statut"  className="form-check-input" checked={statut}    onChange={ onCheckboxChange } /> {(statut == true) ? 'Catègorie Produit Active' : 'Catègorie Produit Inactif'}
       
              </div>

    
              <hr/>
              <button className="btn btn-primary btn-sm" type="submit"><i className="bi bi-save-fill"></i> &nbsp;
                {id <= 0 ? 'Créer nouvelle Catègorie Produit' : 'Modifier Catègorie Produit'}
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