import { useContext, useEffect, useState } from "react";
import { ProduitContext } from "../../context/produit/ProduitContext";
import { ProduitFormDetail } from "./ProduitFormDetail";
import { AuthContext } from "../../auth/context/AuthContext";
import { CatProduitContext } from "../../context/catproduit/CatProduitContext";

export const ProduitForm = ({ selectRegistre, optionForm }) =>{

    const { initEstructure, errors, handlerAddRegistre, registresCatProd, handerFormClose, listTypePlat } = useContext(ProduitContext); //userEstructure, handlerAddUser
    const { login } = useContext(AuthContext);   
    const [useForm, setUseForm] = useState(initEstructure);
    const { id, catProduit, user, code, nom, description, prix, type_plat, createdOn, updatedOn, statut  } = useForm;
    const [checked, setChecked]= useState(useForm.statut);
    const [errorsf, setErrorsf] = useState({});


  const validation = (useForm) =>{
    const errorsf = {}
    const num_pattern = new RegExp(/^\d+(?:[.,]\d+)?$/);

    if(useForm.catProduit == 0){
        errorsf.catProduit = "Vous devez sélectionner une catégorie de produit! ";
    }else{

      errorsf.catProduit = "";
    }
    if(useForm.prix == 0 || useForm.prix == ""){
        errorsf.prix = "Le champ prix est requis ! ";
    }
    else if(!num_pattern.test(useForm.prix)){
        errorsf.prix = "Le format prix n'est pas correct (Uniquement des numéros)!.";
        useForm.prix = 0;
    }else{
      errorsf.prix = "";
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
     
    if(errorsf.prix == '' &&  errorsf.catProduit == '' ){
      errorsf.prix = undefined;
      errorsf.catProduit = undefined;

      let catprod = useForm.catProduit ;
      useForm.catProduit =  {
             "id": catprod
         };
      let user = useForm.user ;
      useForm.user = {
            "id": user
      }

      handlerAddRegistre(useForm);

    }

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
          <input type="hidden" className="form-control" name="user" value={login.user.id}  />


          <div className="row align-items-center mt-1">
            <div className="col">
              <label className="col-sm-12 control-label small">Choisissez la catégorie de produits: </label>
              
              <select className="form-select" aria-label="Default select example" name="catProduit"  onChange={ onInputChange }>    
                {catProduit.id <= 0 ? <option value="0" selected>Catégorie de produits</option> : <option value={catProduit} selected>{
                  
                  registresCatProd.map(registre =>{
                    if(registre.id == catProduit){
    
                      return registre?.nom ;
                    }
                  })
                }</option>}
                {registresCatProd.map(registre =>{
                  if(registre.id != catProduit){
                    return (<option value={registre.id}>{registre.nom}</option>);
                }
                })}
              </select>


              <p className="text-danger small ">{errors?.catProduit} {errorsf?.catProduit}</p>
            </div>
            <div className="col">
              <label className="col-sm-12 control-label small">Choisissez le type de plat: </label>
              <select className="form-select" aria-label="Default select example" name="type_plat"  onChange={ onInputChange }>
                {type_plat == "" ? <option selected>Type de plat</option> : <option value={type_plat} selected>{type_plat}</option> }

                {listTypePlat.map(tPlat =>{
                  if(tPlat != type_plat){
                    return (<option value={tPlat}>{tPlat}</option>);
                  }
                })}

                </select>

              <p className="text-danger small">{errors?.type_plat}</p>
            </div>
          </div>




          <div className="row align-items-center mt-1">
            <div className="col">
              <label className="col-sm-6 control-label small">Code Produit: </label>
              <input type="text" className="form-control" placeholder="Code Produit" name="code" value={code}  onChange={ onInputChange }/>
              <p className="text-danger small ">{errors?.code}</p>
            </div>
            <div className="col">
              <label className="col-sm-6 control-label small">Prix: </label>
              <input type="text" className="form-control" placeholder="Prix" name="prix" value={prix}  onChange={ onInputChange } />
              <p className="text-danger small">{errors?.prix} {errorsf?.prix} </p>
            </div>
          </div>


          
          
          <div className="row justify-content-start mt-1">
            <div className="col">
              <div className="form-check">
              <input type="checkbox" name="statut"  className="form-check-input" checked={statut}    onChange={ onCheckboxChange } /> {(statut == true) ? ' Produit Active' : ' Produit Inactif'}
              </div>
              <hr/>
              <button className="btn btn-primary btn-sm" type="submit"><i className="bi bi-save-fill"></i> &nbsp;
                {id <= 0 ? 'Créer nouvelle  Produit' : 'Modifier Produit'}
              </button>
              {!handerFormClose || <button className="btn btn-primary mx-2 btn-sm" type="button" onClick={ onCloseForm }><i className="bi bi-box-arrow-in-right"></i> &nbsp; Fermer</button>}
            </div>



          </div>

    </form>
    </>
    :
    <>
       <ProduitFormDetail useForm={useForm} handerFormClose={handerFormClose} onCloseForm={onCloseForm} />   

    
    </>);



}