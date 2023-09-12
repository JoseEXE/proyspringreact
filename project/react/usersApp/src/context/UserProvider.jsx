import { useUsers } from "../hooks/useUsers";
import { UserContext } from "./UserContext";



export const UserProvider = ({ children }) => {
    
    const {
        users,
        selectUser,
        selectMyUser,
        paginator,
        userEstructure,
        resPass,
        handlerAddUser,
        handlerDelateUser,
        handlerSelectUser,
        handlerChangerPass,
        visibleForm,
        handlerFormOpen,
        handerFormClose,
        getUsers,
        getMyUser,
        errors,
    } = useUsers();
    
    return(
        <UserContext.Provider value={ 
            {
                users,
                selectUser,
                selectMyUser,
                paginator,
                userEstructure,
                resPass,
                handlerAddUser,
                handlerDelateUser,
                handlerSelectUser,
                handlerChangerPass,
                visibleForm,
                handlerFormOpen,
                handerFormClose,
                getUsers,
                getMyUser,
                errors,
            }
        }>
            {children}
        </UserContext.Provider>
    );
}