const initialState = { session : false }

const sessionReducer = (state = initialState, action) => {
    switch(action.type) {
        case 'login':
            return {... state, session: state.session = true};

        case 'logout':
            return {... state, session: state.session = false};
        
        default:
            return state;
    }
}