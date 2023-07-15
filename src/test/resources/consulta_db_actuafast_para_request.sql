SELECT 
	e.id, e.numero_proceso, e.nombre_cliente,
	e.conteo_grupo1 + e.conteo_grupo2 + e.conteo_grupo3 + e.conteo_grupo4 as numero_empleados, 
	json_build_object(
		'numeroProceso', e.numero_proceso,
		'parametros', json_build_object(
			'fechaValoracion', TO_CHAR(e.fecha_valoracion :: DATE, 'yyyy-mm-dd'),
			'fechaEstimacion', TO_CHAR(e.fecha_estimacion :: DATE, 'yyyy-mm-dd')
		),
		'empresa', json_build_object(
			'estado', estado_empresa.codigo
		),
		'hipotesiscompanyl', json_build_object(
			'decimoCuartoSueldo', h.decimo_cuarto_sueldo
		),
		'empleados', (
			SELECT json_agg(
				json_build_object(
					'identificacion', pe.identificacion_persona,
					'fechaIngresoJubilacion', TO_CHAR(pe.fecha_ingreso_jubilacion :: DATE, 'yyyy-mm-dd'),
					'fechaNacimiento', TO_CHAR(pe.fecha_nacimiento_persona :: DATE, 'yyyy-mm-dd'),
					'tipo', pe.tipo_calculado,
					'sexo', pe.sexo_persona,
					'remuneracionPromedioJubilacion', pe.remuneracion_promedio
				)
			)
			FROM persona_estudio pe
			WHERE pe.id_estudio = e.id
		)
	)
FROM estudio e 
JOIN hipotesis_companyl h ON e.id = h.id_estudio
JOIN estado_empresa estado_empresa ON estado_empresa.id = e.id_estado_empresa
WHERE e.numero_proceso = '114749';