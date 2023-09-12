import { useContext, useEffect, useState } from "react";
import Swal from "sweetalert2";
import { UserContext } from "../../context/UserContext";


// handlerAddUser, userEstructure,no se utilizzan

export const UserForm = ({ selectUser, handerFormClose}) =>{

    const { userEstructure, handlerAddUser, errors  } = useContext(UserContext);
    const [useForm, setUseForm] = useState(userEstructure);
    const { id, nameU, lastName, email, password, admin } = useForm;
    console.log("Checked useForm.admin: "+ useForm.admin);
    const [checked, setChecked]= useState(useForm.admin);

    useEffect(() => {

        setUseForm({ 
            ...selectUser 
        });

    },[selectUser])


    const onInputChange = ({ target }) => {
        console.log(target.value);
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
            admin: checked,
        
        })
    }

    const onSubmit = (event) =>{
        event.preventDefault();
        /* Val */
        // if(!nameU || !lastName || !email || !password){

        //     Swal.fire({
        //         icon: 'error',
        //         title: 'Error de validation...',
        //         text: 'Vouz devez completer le formulaire !',
        //       })
        //     return;
        // }
        // if(!email.includes('@')){
        //     Swal.fire({
        //         icon: 'error',
        //         title: 'Error de email...',
        //         text: 'L\'email doit être valide!...',
        //       })
        //     return;
        // }
        // Sauvegarder l'utilisateur dans la liste users:
        handlerAddUser(useForm);

        //setUseForm(userEstructure);
    }


    const onCloseForm = () => {
        handerFormClose();
        setUseForm(userEstructure);
    
    }



    return(
    <form onSubmit={ onSubmit }>
        <input className="form-control my-3 w-75" placeholder="Nom" name="nameU" value={ nameU } onChange={ onInputChange }  />
        <p className="text-danger">{errors?.nameU}</p>
        <input className="form-control my-3 w-75" placeholder="Prenom" name="lastName" value={ lastName } onChange={ onInputChange }  />
        <p className="text-danger">{errors?.lastName}</p>
        <input className="form-control my-3 w-75" placeholder="email" name="email" value={ email } onChange={ onInputChange }  />
        <p className="text-danger">{errors?.email}</p>

        <div className="my-3 form-check">
        <input type="checkbox" name="admin" checked={admin} 
        className="form-check-input"
        onChange={ onCheckboxChange }
        />
        <label className="form-check-label">Admin</label>
        </div>
        

        {id > 0 || <input className="form-control my-3 w-75" placeholder="password" name="password"  type="password" value={ password } onChange={ onInputChange }  />}
        <p className="text-danger">{errors?.password}</p>
        
        <button className="btn btn-primary" type="submit">
            {id <= 0 ? 'Créer nouvel utilisateur' : 'Modifier utilisateur'}
        </button>
        {!handerFormClose || <button className="btn btn-primary mx-2" type="button" onClick={ onCloseForm }>Fermer</button>}
        
    </form>
    
    );

};