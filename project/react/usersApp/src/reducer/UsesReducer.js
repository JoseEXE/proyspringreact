export const UsesReducer = (state = {}, action) => {

    switch (action.type) {
        case 'addUser':
            
            return [
                ...state,
                {	
                    ...action.payload,
                   // id: new Date().getTime(),

                
                }
        ];
        case 'changerPass' :
            return action.payload;

  

        case 'loadingMyUser' :
            return action.payload;

        case 'updateUser':

            return state.map(u => {
                if(u.id === action.payload.id){
                    return {
                        ...action.payload
                    };

                }
                return u;
            });

        case 'delUser':
    
            return state.filter(user => user.id !== action.payload);
        
        case 'loadingUsers':
       
            return action.payload.content;
        
        case 'loadingPagination':
            return action.payload;
            

        default:
           return state;
    }
}