export const UserPassInfo = ({ selectMyUser }) => {

    return(<>
    <div className="col-lg-5 border m-2 mb-5 p-2">
                    <h5 className="m-3">Votre information : </h5>
                        <hr/>
                        <div className="form-group my-2">
                            <label className="col-sm-6 control-label ">Nom : </label>
                            <div className="col-sm-8">
                                <input  type="text" className="form-control" placeholder="Nom" name="nameU" value={ selectMyUser.nameU } disabled />
                            </div>
                        </div>
                        <div className="form-group my-2">
                            <label className="col-sm-6 control-label ">Prenom :</label>
                            <div className="col-sm-8">
                                <input type="text" className="form-control" placeholder="Prenom" name="lastName" value={ selectMyUser.lastName } disabled  />
                            </div>
                        </div>
                        <div className="form-group my-2">
                            <label className="col-sm-6 control-label ">Email :</label>
                            <div className="col-sm-8">
                                <input type="text" className="form-control" placeholder="Email" name="email" value={ selectMyUser.email } disabled  />
                            </div>
                        </div>
 


                        <div className="form-group">
                            <div className="checkbox inline">
                                <label>
                                    <input type="checkbox" 
                                    name="admin" checked={selectMyUser.admin} 
                                    className="form-check-input"
                                    disabled />
                                    <span className="cc"><i className="cc-icon glyphicon glyphicon-ok"></i></span> Admin
                                </label>
                            </div>

                       
                        </div>


                    </div>
    </>);
}