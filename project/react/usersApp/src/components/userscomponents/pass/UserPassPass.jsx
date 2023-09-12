import { useContext } from "react";
import { useState } from "react";
import Swal from "sweetalert2";
import { UserContext } from "../../../context/UserContext";
import { useEffect } from "react";




export const UserPassPass = ({ selectMyUser }) =>{



   const { errors, handlerChangerPass, userEstructure, resPass } = useContext(UserContext);

    const [values, setValues] = useState({
        passA: '',
        pass1: '',
        pass2: '',
    });
    
    console.log("resPass: "+resPass);

    const [errorsf, setErrorsf] = useState({});
    
    function handleInput(event){
        const newObject = {...values, [event.target.name]: event.target.value}
        setValues(newObject)
    }
    
    function handlevalidation(event) {
        event.preventDefault();
        setErrorsf(validation(values));

        if(errorsf.passA != '' && errorsf.pass1 != '' && errorsf.pass2 != ''){
            console.log("Envoi d'informations....=> " );

            const userEstrucPass = {
                id: selectMyUser.id,
                oldPassword: values.passA, 
                newFPassword: values.pass1,
                newPassword: values.pass2
            }


            if(handlerChangerPass(userEstrucPass) == true){
                setValues({
                    passA: '',
                    pass1: '',
                    pass2: '',
                })
            }
              
        }
       
    }

    const onPassFinish = () =>{
        setUserPass(userEstrucPass);
    };

    const validation = (values) =>{
        console.log("entra a valider");
        const errorsf = {}
        const email_pattern = new RegExp(/^[A-Za-z0-9][A-Za-z0-9.-_]+[A-Za-z0-9][@][A-Za-z0-9][A-Za-z0-9.-_]+[A-Za-z0-9]?[\.][A-Za-z0-9]{2,3}$/);
        const password_pattern = new RegExp(/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,20}$/);
    
        
        if(values.passA === ""){
            errorsf.passA = "Le champ du mot de passe est requis! ";
        }
    
        if(values.pass1 === ""){
            errorsf.pass1 = "Le champ du mot de passe est requis ! ";
        }
        else if(!password_pattern.test(values.pass1)){
            errorsf.pass1 = "Le format du mot de passe n'est pas correct!.";
        }
            
        if(values.pass2 === ""){
            errorsf.pass2 = "Le champ du mot de passe est requis ! ";
        }
        else if(!password_pattern.test(values.pass2)){
            errorsf.pass2 = "Le format du mot de passe n'est pas correct!.";
        }
   

        else if(values.pass2 !== values.pass1){
            errorsf.pass2 = "Les mots de passe doivent être identiques!.";
        }

    return errorsf;
    
    }

    
    return (<>
            <form onSubmit={ handlevalidation } className="col-lg-5 border  m-2 shadow p-3 mb-5 bg-white rounded w-50">
                <div >
                    <h5>Password: </h5>
                    <hr/>
                    <div className="form-group my-2">
                        <label className="col-sm-6 control-label">Mot de passe actuel :</label>
                        <div className="col-sm-8">
                            <input type="password" className= "form-control" name="passA" id="passA" value={ values.passA }  onChange={ handleInput }/>
                             <p className="text-danger">{errorsf?.passA} {errors?.passA}</p>
                        </div>
                    </div>
                    <div className="form-group my-2">

                        <label  className="col-sm-6 control-label">Nouveau mot de passe :</label>
                        <div className="col-sm-8">
                            <input type="password" className="form-control" name="pass1" id="pass1" value={ values.pass1 }  onChange={ handleInput }/>
                            <p className="text-danger">{errorsf?.pass1}</p>
                        </div>
                    </div>

                    <div className="form-group my-2">
                        <label className="col-sm-8 control-label">Répétez le nouveau mot de passe :</label>
                        <div className="col-sm-8">
                            <input type="password" className= "form-control" name="pass2" id="pass2" value={ values.pass2 }  onChange={ handleInput }/>
                            <p className="text-danger">{errorsf?.pass2}</p>

                        </div>
                    </div>
                    <div className="form-group">
                        <label className="col-sm-6 control-label"></label>
                        <div className="col-sm-8">
                        <button className="btn btn-primary" type="submit">Changer le mot de passe</button>
                        </div>
                    </div>
                    <hr />
                    

                </div>
            </form>
    </>);
} 