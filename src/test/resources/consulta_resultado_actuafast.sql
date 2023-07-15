SELECT json_build_object(
	'trabajadoresActivos', info_dem.trabajadores_activos,
	'trabajadoresJubilados', info_dem.trabajadores_jubilados,
	'trabajadoresJubiladosPendientes', info_dem.trabajadores_jubilados_pendientes,
	'trabajadoresSalidos', info_dem.trabajadores_salidos,
	'trabajadoresTipo1Hombres', info_dem.trabajadores_tipo_1_hombres,
	'trabajadoresTipo1Mujeres', info_dem.trabajadores_tipo_1_mujeres,
	'trabajadoresTipo2Hombres', info_dem.trabajadores_tipo_2_hombres,
	'trabajadoresTipo2Mujeres', info_dem.trabajadores_tipo_2_mujeres,
	'trabajadoresTipo3Hombres', info_dem.trabajadores_tipo_3_hombres,
	'trabajadoresTipo3Mujeres', info_dem.trabajadores_tipo_3_mujeres,
	'trabajadoresTipo4Hombres', info_dem.trabajadores_tipo_4_hombres,
	'trabajadoresTipo4Mujeres', info_dem.trabajadores_tipo_4_mujeres,
	'tsPromedioActivos', info_dem.tiempo_servicio_promedio_activos,
	'tfPromedioActivos', e.tf_promedio_activos,
	'edadPromedioActivos', info_dem.edad_promedio_activos,
	'nominaTotalActivos', info_dem.nomina_total_activos,
	'nominaTotalJubilados', info_dem.nomina_total_jubilados,
	'vidaLaboralPromedioRemanente', info_dem.vida_laboral_promedio_remanente,
	'ingresoMensualPromedioActivos', info_dem.ingreso_mensual_promedio_activos,
	'pensionMensualPromedioJubilados', info_dem.pension_mensual_promedio_jubilados,
	'ingresoNomina', info_dem.ingreso_nomina
)
FROM public.estudio e
        LEFT JOIN public.informacion_demografica info_dem ON info_dem.id_estudio = e.id
WHERE e.numero_proceso = '111884';