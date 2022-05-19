import React, {Component} from 'react';
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {faEdit, faUserEdit} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

import AccountNavbar from "../../component/AccountNavbar/AccountNavbar";
import {fetchUser, updateUser} from "../../actions/admin-actions";

class EditUser extends Component {
    state = {
        id:"",
        username: "",
        roles: "",
        email: "",
        password: "",
        active: "",
        perfumeList: [],
        activationCode:"",
        passwordResetCode:""
    };

    componentDidMount() {
        this.props.fetchUser(this.props.match.params.id);
    }

    componentWillReceiveProps(nextProps, nextContext) {
        this.setState({
            ...nextProps.user
        });
    }

    onFormSubmit = (event) => {
        event.preventDefault();

        const user = this.state;
        const bodyFormData = new FormData();
        bodyFormData.append("id", user.id);
        bodyFormData.append("username", user.username);
        bodyFormData.append("roles", user.roles);
        bodyFormData.append("email", user.email);
        bodyFormData.append("password", user.password);
        bodyFormData.append("active", user.active);
        bodyFormData.append("perfumeList", user.perfumeList);
        bodyFormData.append("activationCode", user.activationCode);
        bodyFormData.append("passwordResetCode", user.passwordResetCode);

        this.props.updateUser(bodyFormData)
            .then(() => {
                if (this.props.success) {
                    this.setState({
                        ...this.initialState,
                        showToast: true
                    });
                    setTimeout(() => this.setState({showToast: false}), 5000);
                    window.scrollTo(0, 0);
                }
            });
    }

    handleInputChange = (event) => {
        const {name, value} = event.target;

        this.setState({
            [name]: value
        });
    };

    render() {
        const {username, roles} = this.state;

        return (
            <div>
                <AccountNavbar/>
                <div className="container mt-5">
                    <h4><FontAwesomeIcon className="mr-2" icon={faUserEdit}/> Người dùng: {username}</h4>
                    <form onSubmit={this.onFormSubmit}>
                        <div className="form-group row mt-5">
                            <label className="col-sm-2 col-form-label">Tên người dùng: </label>
                            <div className="col-sm-4">
                                <input
                                    type="text"
                                    className="form-control"
                                    name="username"
                                    value={username}
                                    onChange={this.handleInputChange}/>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label className="col-sm-2 col-form-label">Vai trò: </label>
                            <div className="col-sm-6">
                                <div className="form-check form-check-inline">
                                    <label className="form-check-label mr-1" htmlFor="inlineRadio1">USER</label>
                                    <input
                                        id="inlineRadio1"
                                        type="radio"
                                        className="form-check-input"
                                        name="roles"
                                        value={roles}
                                        onChange={this.handleInputChange}/>
                                </div>
                                <div className="form-check form-check-inline">
                                    <label className="form-check-label mr-1" htmlFor="inlineRadio2">ADMIN</label>
                                    <input
                                        id="inlineRadio1"
                                        type="radio"
                                        className="form-check-input"
                                        name="roles"
                                        value={roles}
                                        onChange={this.handleInputChange}/>
                                </div>
                            </div>
                        </div>
                        <button type="submit" className="btn btn-dark">
                            <FontAwesomeIcon className="mr-2" icon={faEdit}/>Save
                        </button>
                    </form>
                </div>
            </div>
        );
    }
}

EditUser.propTypes = {
    fetchUser: PropTypes.func.isRequired,
    user: PropTypes.object.isRequired,
    updateUser: PropTypes.func.isRequired,
    success: PropTypes.bool.isRequired,
};

const mapStateToProps = (state) => ({
    user: state.admin.user,
    success: state.admin.success,
});

export default connect(mapStateToProps, {fetchUser, updateUser})(EditUser);
