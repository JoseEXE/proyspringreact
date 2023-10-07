import dayjs from "dayjs";

export const ClientFormDetail = ({ useForm, handerFormClose, onCloseForm }) => {
    const { id, nom, prenom, tel, createdOn, updatedOn, statut  } = useForm;


    return (<>
    <form >


        <div className="row">
        </div>
        <div className="row align-items-center">
            <div className="col mt-1">
                <label className="col-sm-6 control-label small">Nom: </label>
                <input type="text" className="form-control" placeholder="Nom" name="nom" value={nom} disabled />
            </div>
        </div>

        <div className="row align-items-center">
            <div className="col mt-1">
                <label className="col-sm-6 control-label small">Prenom: </label>
                <input type="text" className="form-control" placeholder="Prenom" name="prenom" value={prenom} disabled/>
            </div>
        </div>

        <div className="row align-items-center">
            <div className="col mt-1">
                <label className="col-sm-6 control-label small">Tel: </label>
                <input type="text" className="form-control" placeholder="Numéro téléphone" name="tel" value={tel} disabled />
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
              <input type="checkbox" name="statut"  className="form-check-input" checked={statut}  disabled />  {(statut == true) ? 'Client Active' : 'Client Inactif'}
            
              </div>

    
              <hr/>

        {!handerFormClose || <button className="btn btn-primary mx-2 btn-sm" type="button" onClick={ onCloseForm }><i className="bi bi-box-arrow-in-right"></i> &nbsp; Fermer</button>}
            </div>



          </div>

    </form>
    </>);
    
}