package es.uniovi.inf_movil.tanteo

import androidx.lifecycle.ViewModel

class TanteoVM: ViewModel() {

    var scoreLocal: TeamPoints = TeamPoints(0,0,0)
    var scoreVisit: TeamPoints = TeamPoints(0, 0, 0)

}
