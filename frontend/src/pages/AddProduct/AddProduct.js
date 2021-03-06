import React, {Component} from 'react';
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {faPlusSquare} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

import ToastShow from "../../component/Toasts/ToastShow";
import AccountNavbar from "../../component/AccountNavbar/AccountNavbar";
import {addPerfume, formReset} from "../../actions/admin-actions";
import {fetchBrands} from "../../actions/perfume-actions";

class AddProduct extends Component {
    initialState = {
        perfumeTitle: "",
        brandId: 1,
        year: "",
        country: "",
        type: "",
        volume: "",
        perfumeGender: "Nam",
        fragranceTopNotes: "",
        fragranceMiddleNotes: "",
        fragranceBaseNotes: "",
        price: "",
        file: null
    };

    state = {
        ...this.initialState,
        showToast: false
    };

    componentDidMount() {
        this.props.formReset();
        this.props.fetchBrands();
    }

    onFormSubmit = (event) => {
        event.preventDefault();


        const {
            perfumeTitle, brandId, year, country, type, volume, perfumeGender, fragranceTopNotes, fragranceMiddleNotes,
            fragranceBaseNotes, price, file
        } = this.state;
        console.log(brandId);
        const bodyFormData = new FormData();

        bodyFormData.append("file", file);
        bodyFormData.append("perfumeTitle", perfumeTitle);
        bodyFormData.append("brandId", brandId);
        bodyFormData.append("year", year);
        bodyFormData.append("country", country);
        bodyFormData.append("type", type);
        bodyFormData.append("volume", volume);
        bodyFormData.append("perfumeGender", perfumeGender);
        bodyFormData.append("fragranceTopNotes", fragranceTopNotes);
        bodyFormData.append("fragranceMiddleNotes", fragranceMiddleNotes);
        bodyFormData.append("fragranceBaseNotes", fragranceBaseNotes);
        bodyFormData.append("price", price);

        this.props.addPerfume(bodyFormData)
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
    };

    handleFileChange = (event) => {
        this.setState({
            file: event.target.files[0]
        });
    };

    handleInputChange = (event) => {
        const {name, value} = event.target;

        this.setState({
            [name]: value
        });
    };

    render() {
        const {
            perfumeTitle, brandId, year, country, type, volume, perfumeGender, fragranceTopNotes, fragranceMiddleNotes,
            fragranceBaseNotes, price, showToast
        } = this.state;

        const {
            perfumeTitleError, perfumerError, yearError, countryError, typeError, volumeError,
            perfumeGenderError, fragranceTopNotesError, fragranceMiddleNotesError, fragranceBaseNotesError,
            priceError
        } = this.props.errors;

        const {brands} = this.props;

        return (
            <div>
                <AccountNavbar/>
                <div className="container" style={{"display": showToast ? "block" : "none"}}>
                    <ToastShow showToast={showToast} message={"Perfume successfully added!"}/>
                </div>
                <div className="container mt-5">
                    <h4><FontAwesomeIcon className="mr-2" icon={faPlusSquare}/>Th??m s???n ph???m</h4>
                    <br/>
                    <form onSubmit={this.onFormSubmit}>
                        <div className="form row">
                            <div className="col">
                                <label>Perfume title: </label>
                                <input
                                    type="text"
                                    className={perfumeTitleError ? "form-control is-invalid" : "form-control"}
                                    name="perfumeTitle"
                                    value={perfumeTitle}
                                    placeholder="Enter the perfume title"
                                    onChange={this.handleInputChange}/>
                                <div className="invalid-feedback">{perfumeTitleError}</div>
                            </div>
                            {/*<div className="col">*/}
                            {/*    <label>Brand: </label>*/}
                            {/*    <input*/}
                            {/*        type="text"*/}
                            {/*        className={perfumerError ? "form-control is-invalid" : "form-control"}*/}
                            {/*        name="perfumer"*/}
                            {/*        value={perfumer}*/}
                            {/*        placeholder="Enter the brand"*/}
                            {/*        onChange={this.handleInputChange}/>*/}
                            {/*    <div className="invalid-feedback">{perfumerError}</div>*/}
                            {/*</div>     */}
                            <div className="col">
                                <label>Brand: </label>
                                <select
                                    className={perfumerError ? "form-control is-invalid" : "form-control"}
                                    name="brandId"
                                    value={brandId}
                                    placeholder="Enter the brand"
                                    onChange={this.handleInputChange}>
                                    {brands.map(b =>
                                        <option value={b.id}>{b.name}</option>
                                    )}
                                </select>
                                <div className="invalid-feedback">{perfumerError}</div>
                            </div>
                        </div>
                        <div className="form row mt-3">
                            <div className="col">
                                <label>N??m ra m???t: </label>
                                <input
                                    type="number"
                                    className={yearError ? "form-control is-invalid" : "form-control"}
                                    name="year"
                                    value={year}
                                    min={0}
                                    placeholder="Enter the release year"
                                    onChange={this.handleInputChange}/>
                                <div className="invalid-feedback">{yearError}</div>
                            </div>
                            <div className="col">
                                <label>N?????c s???n xu???t: </label>
                                <input
                                    type="text"
                                    className={countryError ? "form-control is-invalid" : "form-control"}
                                    name="country"
                                    value={country}
                                    placeholder="Enter the manufacturer country"
                                    onChange={this.handleInputChange}/>
                                <div className="invalid-feedback">{countryError}</div>
                            </div>
                        </div>
                        <div className="form row mt-3">
                            <div className="col">
                                <label>Lo???i: </label>
                                <input
                                    type="text"
                                    className={typeError ? "form-control is-invalid" : "form-control"}
                                    name="type"
                                    value={type}
                                    placeholder="Enter the perfume type"
                                    onChange={this.handleInputChange}/>
                                <div className="invalid-feedback">{typeError}</div>
                            </div>
                            <div className="col">
                                <label>Dung t??ch: </label>
                                <input
                                    type="number"
                                    className={volumeError ? "form-control is-invalid" : "form-control"}
                                    name="volume"
                                    value={volume}
                                    min={0}
                                    placeholder="Enter the volume"
                                    onChange={this.handleInputChange}/>
                                <div className="invalid-feedback">{volumeError}</div>
                            </div>
                        </div>
                        <div className="form row mt-3">
                            <div className="col">
                                <label>Gi???i t??nh: </label>
                                <select
                                    type="text"
                                    className={perfumeGenderError ? "form-control is-invalid" : "form-control"}
                                    name="perfumeGender"
                                    value={perfumeGender}
                                    placeholder="Enter the gender"
                                    onChange={this.handleInputChange}>
                                    <option value='nam'>Nam</option>
                                    <option value='n???'>N???</option>
                                </select>
                                <div className="invalid-feedback">{perfumeGenderError}</div>
                            </div>
                            <div className="col">
                                <label>Top notes: </label>
                                <input
                                    type="text"
                                    className={fragranceTopNotesError ? "form-control is-invalid" : "form-control"}
                                    name="fragranceTopNotes"
                                    value={fragranceTopNotes}
                                    placeholder="Enter the top notes"
                                    onChange={this.handleInputChange}/>
                                <div className="invalid-feedback">{fragranceTopNotesError}</div>
                            </div>
                        </div>
                        <div className="form row mt-3">
                            <div className="col">
                                <label>Heart notes: </label>
                                <input
                                    type="text"
                                    className={fragranceMiddleNotesError ? "form-control is-invalid" : "form-control"}
                                    name="fragranceMiddleNotes"
                                    value={fragranceMiddleNotes}
                                    placeholder="Enter the heart notes"
                                    onChange={this.handleInputChange}/>
                                <div className="invalid-feedback">{fragranceMiddleNotesError}</div>
                            </div>
                            <div className="col">
                                <label>Base notes: </label>
                                <input
                                    type="text"
                                    className={fragranceBaseNotesError ? "form-control is-invalid" : "form-control"}
                                    name="fragranceBaseNotes"
                                    value={fragranceBaseNotes}
                                    placeholder="Enter the base notes"
                                    onChange={this.handleInputChange}/>
                                <div className="invalid-feedback">{fragranceBaseNotesError}</div>
                            </div>
                        </div>
                        <div className="form row mt-3">
                            <div className="col">
                                <label>Gi??: </label>
                                <input
                                    type="number"
                                    className={priceError ? "form-control is-invalid" : "form-control"}
                                    name="price"
                                    value={price}
                                    min={0}
                                    placeholder="Enter the price"
                                    onChange={this.handleInputChange}/>
                                <div className="invalid-feedback">{priceError}</div>
                            </div>
                            <div className="col" style={{marginTop: "35px"}}>
                                <input type="file"
                                       name="file"
                                       onChange={this.handleFileChange}/>
                            </div>
                        </div>
                        <button type="submit" className="btn btn-dark mt-3">
                            <FontAwesomeIcon className="mr-2" icon={faPlusSquare}/>Add
                        </button>
                    </form>
                </div>
            </div>
        );
    }
}

AddProduct.propTypes = {
    addPerfume: PropTypes.func.isRequired,
    formReset: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired,
    success: PropTypes.bool.isRequired,
    fetchBrands: PropTypes.func.isRequired,
    brands: PropTypes.array.isRequired
};

const mapStateToProps = (state) => ({
    errors: state.admin.errors,
    success: state.admin.success,
    brands: state.brand.brands
});

export default connect(mapStateToProps, {addPerfume, formReset, fetchBrands})(AddProduct);
