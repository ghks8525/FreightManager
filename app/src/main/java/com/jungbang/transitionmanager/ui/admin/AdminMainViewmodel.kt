package com.jungbang.transitionmanager.ui.admin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jungbang.transitionmanager.model.dto.VehicleAdd
import com.jungbang.transitionmanager.model.dto.VehicleDelete
import com.jungbang.transitionmanager.model.dto.VehicleEdit
import com.jungbang.transitionmanager.model.dto.Vehicles
import com.jungbang.transitionmanager.model.http.VehicleAddProtocol
import com.jungbang.transitionmanager.model.http.VehicleDeleteProtocol
import com.jungbang.transitionmanager.model.http.VehicleEditProtocol
import com.jungbang.transitionmanager.model.http.VehiclesProtocol
import com.jungbang.transitionmanager.sys.net.HttpResponsable
import com.jungbang.transitionmanager.sys.net.NetworkManager
import com.jungbang.transitionmanager.sys.net.ProtocolFactory

class AdminMainViewmodel: ViewModel() {
    val mldVehicles: MutableLiveData<Vehicles.Response.Data> = MutableLiveData()

    fun getVehicles(){
        val protocol: VehiclesProtocol = ProtocolFactory.create(VehiclesProtocol::class.java)

        protocol.setHttpResponsable(object  :HttpResponsable<Vehicles.Response.Data>{
            override fun onResponse(res: Vehicles.Response.Data) {
                mldVehicles.value = res
            }

            override fun onFailure(nError: Int, strMsg: String) {
                super.onFailure(nError, strMsg)
            }
        })

        NetworkManager.getInstance().asyncRequest(protocol)
    }

    fun addVehicle(data: VehicleAdd.Request){
        val protocol: VehicleAddProtocol = ProtocolFactory.create(VehicleAddProtocol::class.java)

        protocol.setJsonRequestBody(data)
        protocol.setHttpResponsable(object  :HttpResponsable<VehicleAdd.Response>{
            override fun onResponse(res: VehicleAdd.Response) {
            }

            override fun onFailure(nError: Int, strMsg: String) {
                super.onFailure(nError, strMsg)
            }
        })

        NetworkManager.getInstance().asyncRequest(protocol)
    }

    fun editVehicle(data: VehicleEdit.Request, id: Int){
        val protocol: VehicleEditProtocol = ProtocolFactory.create(VehicleEditProtocol::class.java)

        protocol.PATH = protocol.PATH + id
        protocol.setJsonRequestBody(data)
        protocol.setHttpResponsable(object  :HttpResponsable<VehicleEdit.Response>{
            override fun onResponse(res: VehicleEdit.Response) {
            }

            override fun onFailure(nError: Int, strMsg: String) {
                super.onFailure(nError, strMsg)
            }
        })

        NetworkManager.getInstance().asyncRequest(protocol)
    }

    fun deleteVehicle(id: Int){
        val protocol: VehicleDeleteProtocol = ProtocolFactory.create(VehicleDeleteProtocol::class.java)
        protocol.PATH = protocol.PATH + id

        protocol.setHttpResponsable(object  :HttpResponsable<VehicleDelete.Response>{
            override fun onResponse(res: VehicleDelete.Response) {
            }

            override fun onFailure(nError: Int, strMsg: String) {
                super.onFailure(nError, strMsg)
            }
        })

        NetworkManager.getInstance().asyncRequest(protocol)
    }
}