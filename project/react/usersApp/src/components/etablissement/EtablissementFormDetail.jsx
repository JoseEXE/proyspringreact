import dayjs from "dayjs";

export const EtablissementFormDetail = ({ useFormEtab, handerFormClose, onCloseForm }) =>{

    const { id, nom, siret, rue, cod_postal, ville, num_tel, statut, createdOn, updatedOn } = useFormEtab;

    return(<>
    <div className="row">
          </div>
          <div className="row align-items-center">
            <div className="col mt-1">
              <label className="col-sm-6 control-label small">Nom: </label>
              <input type="text" className="form-control" placeholder="Nom" name="nom" value={nom} disabled />
            </div>
          </div>
          <div className="row align-items-center mt-1">
            <div className="col">
              <label className="col-sm-6 control-label small">Siret: </label>
              <input type="text" className="form-control" placeholder="Siret" name="siret" value={siret}  disabled />
            </div>
          </div>
          <div className="row align-items-center mt-1">
            <div className="col">
              <label className="col-sm-6 control-label small">Rue: </label>
              <input type="text" className="form-control" placeholder="Rue" name="rue" value={rue} disabled />
            </div>
          </div>
          <div className="row align-items-center mt-1">
            <div className="col">
              <label className="col-sm-6 control-label small">Code Postal: </label>
              <input type="text" className="form-control" placeholder="Code Postal" name="cod_postal" value={cod_postal}  disabled />
            </div>
            <div className="col">
              <label className="col-sm-6 control-label small">Ville: </label>
              <input type="text" className="form-control" placeholder="Ville" name="ville" value={ville}  disabled  />
            </div>
          </div>
          <div className="row align-items-center mt-1">
            <div className="col">
            <label className="col-sm-6 control-label small">Téléphone: </label>
              <input type="text" className="form-control" placeholder="Téléphone" name="num_tel" value={num_tel}  disabled  />
            </div>
          </div>

          <div className="row align-items-center mt-1">
            <div className="col">
              <label className="col-sm-6 control-label small">Date de création: </label>
              <input type="text" className="form-control" placeholder="Date de création" name="createdOn" value={dayjs(createdOn).format("DD/MM/YYYY HH:mm:ss A")  }  disabled />
            </div>
            <div className="col">
              <label className="col-sm-8 control-label small">Date de modification: </label>
              <input type="text" className="form-control" placeholder="Date de modification" name="updatedOn" value={dayjs(updatedOn).format("DD/MM/YYYY HH:mm:ss A") }  disabled  />
            </div>
          </div>

          <div className="row justify-content-start mt-1">
            <div className="col">
              <div className="form-check">
              <input type="checkbox" name="statut"  className="form-check-input" checked={statut}  disabled />  {(statut == true) ? 'Etablissement Active' : 'Etablissement Inactif'}
            
              </div>

    
              <hr/>

        {!handerFormClose || <button className="btn btn-primary mx-2 btn-sm" type="button" onClick={ onCloseForm }><i className="bi bi-box-arrow-in-right"></i> &nbsp; Fermer</button>}
            </div>



          </div>
    </>);
}