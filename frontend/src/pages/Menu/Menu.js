import React, {Component} from "react";
import {Route} from "react-router-dom";
import {connect} from "react-redux";
import PropTypes from "prop-types";

import Checkbox from "../../component/CheckBox/Checkbox";
import CheckboxRadio from "../../component/CheckboxRadio/CheckboxRadio";
import MenuCards from "../../component/MenuCards/MenuCards";
import {gender, perfumer, price} from "./MenuData";
import {
    fetchPerfumes,
    fetchPerfumesByPerfumer,
    fetchPerfumesByGender,
    fetchPerfumesByFilterParams, fetchBrands
} from "../../actions/perfume-actions";
import "./MenuStyle.css";

class Menu extends Component {
    state = {
        filterParams: {
            perfumers: [],
            genders: [],
            prices: []
        }
    };

    componentDidMount() {
        const perfumeData = this.props.location.state.id;
        this.props.fetchBrands();
        if (perfumeData === "nữ" || perfumeData === "nam") {
            this.props.fetchPerfumesByGender({perfumeGender: perfumeData});
            window.scrollTo(0, 0);
        } else if (perfumeData === "all") {
            this.props.fetchPerfumes();
            window.scrollTo(0, 0);
        } else if (perfumeData) {
            this.props.fetchPerfumesByPerfumer({brand: perfumeData});
            window.scrollTo(0, 0);
        }

    }

    getProducts = (variables) => {
        this.props.fetchPerfumesByFilterParams(variables);
    };

    handlePrice = (value) => {
        const data = price;
        let array = [];

        for (let key in data) {
            if (data[key].id === parseInt(value, 10)) {
                array = data[key].array;
            }
        }

        return array
    };

    handleFilters = (filters, category) => {
        const newFilters = this.state.filterParams
        newFilters[category] = filters

        if (category === "prices") {
            let priceValues = this.handlePrice(filters)
            newFilters[category] = priceValues
        }

        this.getProducts(newFilters)
        this.setState(newFilters);
    };

    render() {
        const {perfumes} = this.props;
        const {brands} = this.props;
        const brandList = [];
        for (let b of brands) {
            brandList.push({"name": b.name})
        }

        return (
            <div className="container d-flex">
                <nav id="sidebar">
                    <div className="sidebar-header">
                        <h3>Perfumes</h3>
                    </div>
                    <ul className="list-unstyled components">
                        <h5>Thương hiệu</h5>
                        <li className="active mb-2" id="homeSubmenu">
                            <Checkbox list={brandList}
                                      handleFilters={(filters) => this.handleFilters(filters, "brands")}/>
                        </li>
                        <h5>Giới tính</h5>
                        <li className="active mb-2">
                            <Checkbox list={gender}
                                      handleFilters={(filters) => this.handleFilters(filters, "genders")}/>
                        </li>
                        <h5>Giá</h5>
                        <li className="active mb-2">
                            <CheckboxRadio list={price}
                                           handleFilters={(filters) => this.handleFilters(filters, "prices")}/>
                        </li>
                    </ul>
                </nav>
                <Route exact component={() => <MenuCards data={perfumes} itemsPerPage={16} searchByData={[
                    {label: 'Thương hiệu', value: 'brandName'},
                    {label: 'Tên', value: 'perfumeTitle'},
                    {label: 'Nước', value: 'country'}]}/>}/>
            </div>
        );
    }
}

Menu.propTypes = {
    fetchPerfumes: PropTypes.func.isRequired,
    fetchBrands: PropTypes.func.isRequired,
    fetchPerfumesByPerfumer: PropTypes.func.isRequired,
    fetchPerfumesByGender: PropTypes.func.isRequired,
    fetchPerfumesByFilterParams: PropTypes.func.isRequired,
    perfumes: PropTypes.array.isRequired,
    brands: PropTypes.array.isRequired
};

const mapStateToProps = (state) => ({
    perfumes: state.perfume.perfumes,
    brands: state.brand.brands
});

export default connect(mapStateToProps, {
    fetchPerfumes,
    fetchBrands,
    fetchPerfumesByPerfumer,
    fetchPerfumesByGender,
    fetchPerfumesByFilterParams
})(Menu);
