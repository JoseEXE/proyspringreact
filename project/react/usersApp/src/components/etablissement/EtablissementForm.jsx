import { useContext, useEffect, useState } from "react";
import { EtablissementContext } from "../../context/etablissement/EtablissementContext";
import dayjs from "dayjs";
import { EtablissementFormDetail } from "./EtablissementFormDetail";





export const EtablissementForm = ({ selectEtablissement, handerFormClose, optionForm }) =>{

  const { etablissEst, errors, handlerAddEtablissement } = useContext(EtablissementContext); //userEstructure, handlerAddUser
  const [useFormEtab, setUseFormEtab] = useState(etablissEst);
  const { id, nom, siret, rue, cod_postal, ville, num_tel, statut, createdOn, updatedOn } = useFormEtab;
  const [checked, setChecked]= useState(useFormEtab.statut);

  const onSubmit = (event) =>{
    event.preventDefault();
    handlerAddEtablissement(useFormEtab);
}

useEffect(() => {

  setUseFormEtab({ 
      ...selectEtablissement 
  });

},[selectEtablissement])


const onInputChange = ({ target }) => {
  const { name, value } = target;
  setUseFormEtab({
          ...useFormEtab,
          [name]: value,
      })
}
const onCheckboxChange = () =>{
  setChecked(!checked);
  setUseFormEtab({
      ...useFormEtab,
      statut: checked,
  
  })
}
  
const onCloseForm = () => {
  handerFormClose();
  setUseFormEtab(etablissEst);

}

    return(
    optionForm === "edit" || optionForm === "new" ? 
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
              <label className="col-sm-6 control-label small">Siret: </label>
              <input type="text" className="form-control" placeholder="Siret" name="siret" value={siret}  onChange={ onInputChange }/>
              <p className="text-danger small">{errors?.siret}</p>
            </div>
          </div>
          <div className="row align-items-center mt-1">
            <div className="col">
              <label className="col-sm-6 control-label small">Rue: </label>
              <input type="text" className="form-control" placeholder="Rue" name="rue" value={rue}  onChange={ onInputChange }/>
              <p className="text-danger small">{errors?.rue}</p>
            </div>
          </div>
          <div className="row align-items-center mt-1">
            <div className="col">
              <label className="col-sm-6 control-label small">Code Postal: </label>
              <input type="text" className="form-control" placeholder="Code Postal" name="cod_postal" value={cod_postal}  onChange={ onInputChange }/>
              <p className="text-danger small ">{errors?.cod_postal}</p>
            </div>
            <div className="col">
              <label className="col-sm-6 control-label small">Ville: </label>
              <input type="text" className="form-control" placeholder="Ville" name="ville" value={ville}  onChange={ onInputChange } />
              <p className="text-danger small">{errors?.ville}</p>
            </div>
          </div>
          <div className="row align-items-center mt-1">
            <div className="col">
            <label className="col-sm-6 control-label small">Téléphone: </label>
              <input type="text" className="form-control" placeholder="Téléphone" name="num_tel" value={num_tel}  onChange={ onInputChange } />
              <p className="text-danger small">{errors?.num_tel}</p>
            </div>
          </div>
          <div className="row justify-content-start mt-1">
            <div className="col">
              <div className="form-check">
              <input type="checkbox" name="statut"  className="form-check-input" checked={statut}    onChange={ onCheckboxChange } /> {(statut == true) ? 'Etablissement Active' : 'Etablissement Inactif'}
              <input type="hidden" name="createdOn" value={createdOn}  onChange={ onCheckboxChange } />
              </div>

    
              <hr/>
              <button className="btn btn-primary btn-sm" type="submit"><i className="bi bi-save-fill"></i> &nbsp;
            {id <= 0 ? 'Créer nouvel etablissement' : 'Modifier etablissement'}
        </button>
        {!handerFormClose || <button className="btn btn-primary mx-2 btn-sm" type="button" onClick={ onCloseForm }><i className="bi bi-box-arrow-in-right"></i> &nbsp; Fermer</button>}
            </div>



          </div>

    </form>
    </>
    :
    <>
       <EtablissementFormDetail useFormEtab={useFormEtab} handerFormClose={handerFormClose} onCloseForm={onCloseForm} />   

   

    </>);
}